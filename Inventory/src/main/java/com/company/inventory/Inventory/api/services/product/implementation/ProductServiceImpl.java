package com.company.inventory.Inventory.api.services.product.implementation;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.model.ProductEntity;
import com.company.inventory.Inventory.api.repository.IProductoRepository;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.response.product.ProductoResponseRest;
import com.company.inventory.Inventory.api.services.product.IProductService;
import com.company.inventory.Inventory.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductoRepository repository;

    @Autowired
    private com.company.inventory.Inventory.api.category.ICategoryRespository categoryRespository;

    @Override
    public ResponseEntity<ProductoResponseRest> serch() {
        return null;
    }

    @Override
    public ResponseEntity<ProductoResponseRest> findByNameProduct(String name) {
        ProductoResponseRest response = new ProductoResponseRest();
        List<ProductEntity> list = new ArrayList<>();
        List<ProductEntity> listAux = new ArrayList<>();
        try {
            listAux = repository.findByNameContainingIgnoreCase(name);
            if (listAux.size()>0) {
                listAux.stream().forEach((p) -> {
                    byte[] imagenDescompressed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imagenDescompressed);
                    list.add(p);
                });

                response.getProducto().setProductEntities(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrados");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar por nombre");
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponseRest> findById(Long id) {
        ProductoResponseRest response = new ProductoResponseRest();
        List<ProductEntity> list = new ArrayList<>();
        try {
            Optional<ProductEntity> optional = repository.findById(id);
            if (optional.isPresent()) {
                byte[] imagenDescompressed = Util.decompressZLib(optional.get().getPicture());
                optional.get().setPicture(imagenDescompressed);
                list.add(optional.get());
                response.getProducto().setProductEntities(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductoResponseRest> deleteProduct(Long id) {
        ProductoResponseRest response = new ProductoResponseRest();
        try {
            repository.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "producto Eliminado");
        }
        catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar el producto");
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponseRest> saveProduct(ProductEntity product, Integer idcategory) {
        ProductoResponseRest response = new ProductoResponseRest();
        List<ProductEntity> productEntities = new ArrayList<>();
        try {
            Optional<CategoryEntity> category = categoryRespository.findById(idcategory);
            if (category.isPresent()) {
                product.setCategory(category.get());
            } else {
                response.setMetadata("Response nok", "-1","Categoria no encontrada");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            ProductEntity productEntity = repository.save(product);
            if (productEntity != null) {
                productEntities.add(productEntity);
                response.getProducto().setProductEntities(productEntities);
                response.setMetadata("Resgistro producto guardado", "00","Producto guardado");
            }else {
                response.setMetadata("Response nok", "-1","No se logro gurdar producto");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al gurdar el producto");
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponseRest> updateProduct(ProductEntity product, Long id) {
        return null;
    }
}
