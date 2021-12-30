package com.huylq.original.blockchain.controller;

import com.huylq.original.blockchain.entity.Block;
import com.huylq.original.blockchain.entity.BlockChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MiningController {
    private static final Logger log = LoggerFactory.getLogger(MiningController.class);

    private BlockChain blockChain;

    public MiningController() {
        this.blockChain = new BlockChain(2);
    }

    @GetMapping("/mine_block/{data}")
    public Block mineBlock(@PathVariable String data) {
        log.info("[mineBlock] Excute");

        return blockChain.addBlock((Block.builder()
                .data(data)
                .build()));
    }

    @GetMapping("/get_chain")
    public List<Block> getChains() {
        log.info("[getChains] Excute");

        return blockChain.getChains();
    }
}
