package com.haimp02.pointofsales.services;

import java.util.List;
import java.util.Optional;

import com.haimp02.pointofsales.models.entities.Product;
import com.haimp02.pointofsales.models.repositories.ProductRepository;
import com.haimp02.pointofsales.services.interfaces.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(int page) {
        Pageable pagination = PageRequest.of(page, 12);
        return productRepository.findAll(pagination);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> getProduct = productRepository.findById(id);
        if (getProduct.isPresent()) {
            return getProduct.get();
        }
        return null;
    }

    @Override
    public List<Product> findByNameContaining(String search) {
        List<Product> products = productRepository.findByNameContaining(search);
        return products;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        // TODO Auto-generated method stub
        Optional<Product> productUpdate = productRepository.findById(product.getId());
        if (!productUpdate.isEmpty()) {
            productRepository.save(productUpdate.get());
        }
    }

    @Override
    public Boolean isExistsById(Long id) {
        // TODO Auto-generated method stub
        return productRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        productRepository.deleteById(id);        
    }
    
}
