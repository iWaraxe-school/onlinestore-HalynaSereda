package com.coherentsolutions.store;

import com.coherentsolutions.domain.Category;
import com.coherentsolutions.domain.Product;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;
/**
 * Helper class for populating the store with categories and products.
 */
public class StoreHelper {
    private final Store store;
    private static final Logger logger = Logger.getLogger(StoreHelper.class.getName());

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
                    logger.warning("Cannot access constructor of " + subType.getSimpleName() + ". It might be private.");
                    continue;
                }

                Category category = constructor.newInstance();
                store.addCategoryToList(category);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.severe("Error creating category: " + e.getMessage());
            }
        }
    }

    /**
     * Fills the store with products by generating random products for each category.
     */
    public void fillStore (){
        RandomStorePopulator generator = new RandomStorePopulator();
        for (Category category: store.getCategoryList()) {
            for (int i = 0; i < 10; i++) {
               Product product = generator.generateProduct(category.getName());
               category.addProductToCategory(product);

            }
        }
    }
}

