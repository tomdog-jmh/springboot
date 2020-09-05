package com.jmh.springboottest.service;

import org.springframework.http.ResponseEntity;

/**
 * name : jmh
 * time : 2020/9/5 19:29
 */
public interface PoiService {
    /**
     * 导出表格
     */
    ResponseEntity<byte[]> getPoi();
}
