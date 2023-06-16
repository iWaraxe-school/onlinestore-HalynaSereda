package com.coherentsolutions.domain;

import java.awt.print.Book;

public enum Categories {
    BOOK ("Book") , FOOD ("Food") , PHONE("Phone");


   public final String name;

  Categories(String name) {
     this.name = name;
  }

}
