package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

/**
 * Helper class for populating the store with categories and products.
 */
public class StoreHelper {
    private final Store store;
    private static final Logger logger = LoggerFactory.getLogger(StoreHelper.class);

    public StoreHelper(Store store) {
        this.store = store;
               createCategories();
    }

    private synchronized void createCategories() {
        Reflections reflections = new Reflections("com.coherentsolutions.domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType : subTypes) {
            try {
                Constructor<? extends Category> constructor = subType.getDeclaredConstructor();
                if (!constructor.canAccess(null)) {
                    logger.warn("Cannot access constructor of " + subType.getSimpleName() + ". It might be private.");
                    continue;
                }

                Category category = constructor.newInstance();
                store.addCategoryToList(category);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error("Error creating category: " + e.getMessage());
            }
        }
    }

    /**
     * Fills the store with products by generating random products for each category.
     */

    public void fillStore() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            RandomStorePopulator generator = new RandomStorePopulator(connection);
            for (Category category : store.getCategoryList()) {
                for (int i = 0; i < 10; i++) {
                    Product product = generator.generateProduct(category.getName());
                    category.addProductToCategory(product);
                    RandomStorePopulator.insertProductIntoDatabase(product, connection);
                    RandomStorePopulator.insertCategoryIntoDB(category, connection);
                    logger.info("Added product: {} to category: {}", product.getName(), category.getName());
                }
            }
        } catch (SQLException e) {
            logger.error("Error filling the store with products: " + e.getMessage());
        }
    }
    }
