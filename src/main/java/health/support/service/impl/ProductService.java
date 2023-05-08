package health.support.service.impl;

import health.support.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import health.support.dto.ProductDTO;
import health.support.entity.Product;
import health.support.service.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public boolean createProduct(ProductDTO product) {
        productRepository.save(Product.fromDTO(product));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAllProductsForUser(Long userId) {
        final List<ProductDTO> result = new ArrayList<>();
        List<Product> products = productRepository.findAllByUser(userId);

        products.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Override
    @Transactional
    public boolean updateProduct(ProductDTO product) {
        productRepository.save(Product.fromDTO(product));
        return true;
    }

    @Override
    @Transactional
    public boolean deleteProductById(Long id) {
        productRepository.updateDeletedProductById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAllProductsByNameForUser(Long userId, String name) {
        final List<ProductDTO> result = new ArrayList<>();
        List<Product> products = productRepository.findAllByNameForUser(userId, name);

        products.forEach((x) -> result.add(x.toDTO()));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public int getNumberOfProducts(Long userId) {
        return productRepository.getNumberOfRows(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByPageable(Long userId, Pageable pageable) {
        List<ProductDTO> result = new ArrayList<>();
        List<Product> products = productRepository.findUserProductsByPage(userId, pageable).getContent();

        products.forEach((x) -> result.add(x.toDTO()));

        return result;
    }

}
