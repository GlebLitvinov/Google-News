package com.project.googlenews.model.listener;

import com.project.googlenews.model.Item;

public interface IFavoriteListener {
    void setFavorite(Item item);
    void deleteFromFavorite(Item item);
}
