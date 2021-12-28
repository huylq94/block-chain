package com.huylq.original.blockchain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BlockChain {

    private List<Block> chains;

    private Block genesisBlock;

    public BlockChain() {
        this.genesisBlock = new Block("000", "Init genesis");
        this.chains = new ArrayList(Arrays.asList(genesisBlock));
    }

    private Block getLastBlock() {

        return this.chains.get(this.chains.size() - 1);
    }

    public void addBlock(Block block) {
        Block lastBlock = getLastBlock();
        Block newBlock = new Block(lastBlock.getHash(), block.getData());
        newBlock.proofOfWork();
        this.chains.add(newBlock);
    }

    public boolean isValid() {
        for (int i = 1; i < this.chains.size(); i++) {
            Block currentBlock = this.chains.get(i);
            Block prevBlock = this.chains.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.caculateHash()))
                return false;
            if (!currentBlock.getPreviousHash().equals(prevBlock.getHash()))
                return false;
        }
        return true;
    }
}
