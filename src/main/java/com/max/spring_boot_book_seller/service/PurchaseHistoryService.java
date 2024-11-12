package com.max.spring_boot_book_seller.service;

import com.max.spring_boot_book_seller.model.PurchaseHistory;
import com.max.spring_boot_book_seller.repository.IPurchaseHistoryRepository;
import com.max.spring_boot_book_seller.repository.projection.IPurchaseItem;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseHistoryService implements IPurchaseHistoryService {

    private final IPurchaseHistoryRepository purchaseHistoryRepository;

    public PurchaseHistoryService(IPurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public List<IPurchaseItem> findAllPurchasesOfUser(Long userId) {
        return purchaseHistoryRepository.findAllPurchasesOfUser(userId);
    }

}
