package com.huylq.original.blockchain.controller;

import com.huylq.original.blockchain.entity.Block;
import com.huylq.original.blockchain.entity.BlockChain;

public class MiningController {

    public static void main(String[] args) {
        BlockChain kemCoin = new BlockChain(2);
        kemCoin.addBlock(Block.builder()
                .data("Huy")
                .build());
        kemCoin.addBlock(Block.builder()
                .data("Learning Blockchain")
                .build());
        kemCoin.addBlock(Block.builder()
                .data("Java")
                .build());
        kemCoin.getChains().forEach(System.out::println);
        System.out.println(kemCoin.isValid());

    }
}
