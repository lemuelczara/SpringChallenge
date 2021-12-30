package com.example.springchallenge.repository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.springchallenge.entity.Produto;
import com.example.springchallenge.helper.DatabaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProdutoRepository {
    private ObjectMapper objectMapper = DatabaseHelper.getObjectMapper();
    private String PATH = DatabaseHelper.getDatabasePath();
    private List<Produto> produtos = new ArrayList<>(DatabaseHelper.getDatabase());


    public List<Produto> getAll() {
        return new ArrayList<>(produtos);
    }

    public Produto getById(Long productId) {
        //COLOCAR EXCEPTION AQUI PARA O CASO DE NÃO HAVER ID
        Produto produto = produtos.stream().filter(x ->
                x.getProductId().equals(productId))
                .findFirst().orElse(new Produto());
        produto.setPrice(new BigDecimal(String.valueOf(produto.getPrice())).setScale(2, RoundingMode.HALF_EVEN));
        return produto;
    }

    public List<Produto> insertAllArticles(List<Produto> produtos) {
        for (Produto produto : produtos) {
            produto.setProductId((long) this.produtos.size() + 1);
            this.produtos.add(produto);
        }

        updateFile();
        return produtos;
    }

    public void updateFile() {
        try {
            objectMapper.writeValue(new File(PATH), this.produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> getProductsByCategoryAndFreeShippingAndOrder(String categoria, Boolean freeShipping, int order)
            throws IOException {
        return new ArrayList<>(produtos);

    }

    public List<Produto> getProductsByNameAndCategory(String name, String category) {
        return this.produtos.stream()
                .filter(produto -> produto.getName().equalsIgnoreCase(name))
                .filter(produto -> produto.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByNameAndBrand(String name, String brand) {
        return this.produtos.stream()
                .filter(produto -> produto.getName().equalsIgnoreCase(name))
                .filter(produto -> produto.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByNameAndPrice(String name, BigDecimal price) {
        return this.produtos.stream()
                .filter(produto -> produto.getName().equalsIgnoreCase(name))
                .filter(produto -> produto.getPrice().equals(price))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByNameAndFreeShipping(String name, Boolean freeShipping) {
        return this.produtos.stream()
                .filter(produto -> produto.getName().equalsIgnoreCase(name))
                .filter(produto -> produto.getFreeShipping().equals(freeShipping))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByNameAndPrestige(String name, String prestige) {
        return this.produtos.stream()
                .filter(produto -> produto.getName().equalsIgnoreCase(name))
                .filter(produto -> produto.getPrestige().equalsIgnoreCase(prestige))
                .collect(Collectors.toList());
    }


    public List<Produto> getProductsByCategoryAndBrand(String category, String brand) {
        return this.produtos.stream()
                .filter(produto -> produto.getCategory().equalsIgnoreCase(category))
                .filter(produto -> produto.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

	}
    public List<Produto> getByCategory(String category)
    {
        return produtos.stream().filter(produto->produto.getCategory().equals(category)).collect(Collectors.toList());

    }

    public List<Produto> getProductsByCategoryAndPrice(String category, BigDecimal price) {
        return this.produtos.stream()
                .filter(produto -> produto.getCategory().equalsIgnoreCase(category))
                .filter(produto -> produto.getPrice().equals(price))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByCategoryAndFreeShipping(String category, Boolean freeShipping) {
        return this.produtos.stream()
                .filter(produto -> produto.getCategory().equalsIgnoreCase(category))
                .filter(produto -> produto.getFreeShipping().equals(freeShipping))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByCategoryAndPrestige(String category, String prestige) {
        return this.produtos.stream()
                .filter(produto -> produto.getCategory().equalsIgnoreCase(category))
                .filter(produto -> produto.getPrestige().equalsIgnoreCase(prestige))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByBrandAndPrice(String brand, BigDecimal price) {
        return this.produtos.stream()
                .filter(produto -> produto.getBrand().equalsIgnoreCase(brand))
                .filter(produto -> produto.getPrice().equals(price))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByBrandAndFreeShipping(String brand, Boolean freeShipping) {
        return this.produtos.stream()
                .filter(produto -> produto.getBrand().equalsIgnoreCase(brand))
                .filter(produto -> produto.getFreeShipping().equals(freeShipping))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByBrandAndPrestige(String brand, String prestige) {
        return this.produtos.stream()
                .filter(produto -> produto.getBrand().equalsIgnoreCase(brand))
                .filter(produto -> produto.getPrestige().equalsIgnoreCase(prestige))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByPriceAndFreeShipping(BigDecimal price, Boolean freeShipping) {
        return this.produtos.stream()
                .filter(produto -> produto.getPrice().equals(price))
                .filter(produto -> produto.getFreeShipping().equals(freeShipping))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByPriceAndPrestige(BigDecimal price, String prestige) {
        return this.produtos.stream()
                .filter(produto -> produto.getPrice().equals(price))
                .filter(produto -> produto.getPrestige().equalsIgnoreCase(prestige))
                .collect(Collectors.toList());
    }

    public List<Produto> getProductsByFreeShippingAndPrestige(Boolean freeShipping, String prestige) {
        return this.produtos.stream()
                .filter(produto -> produto.getFreeShipping().equals(freeShipping))
                .filter(produto -> produto.getPrestige().equalsIgnoreCase(prestige))
                .collect(Collectors.toList());
    }
}
