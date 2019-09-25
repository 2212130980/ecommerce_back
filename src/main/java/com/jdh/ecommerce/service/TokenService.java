package com.jdh.ecommerce.service;

public interface TokenService {
    String generateToken(  Long userId);
    String save(String key);
    void del(String key);
    void set(String key ,String value);
    void setObj(String key ,Object value);
    Object get(String key);
    Object getObj(String key);
    void saveObj(String key,Object value);
}
