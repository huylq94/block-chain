package com.huylq.original.blockchain.entity;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block {
    private static final Logger LOGGER = LogManager.getLogger(Block.class);
    private String hash;
    private String previousHash;
    private String data;
    private LocalDateTime timeStamp;
    private int proof;

    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
        this.hash = this.calculateHash();
        this.proof = 0;
    }

    public String calculateHash() {
        return DigestUtils.sha256Hex(
                this.previousHash + this.data + this.timeStamp + this.proof
        );
    }

    public void proofOfWork(int difficulty) {
        String prefixString = new String(new char[difficulty]).replace('\0', '0');
        while(!this.hash.startsWith(prefixString)) {
            this.proof++;
            this.hash = calculateHash();
        }
    }

    @Override
    public String toString() {
        return String.format("Hash: %s | Previous Hash: %s | Data: %s | Timestamp: %s | Proof: %d"
                , this.hash, this.previousHash, this.data, this.getTimeStamp().toString(), this.proof);
    }
}
