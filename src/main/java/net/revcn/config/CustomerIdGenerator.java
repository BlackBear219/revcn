package net.revcn.config;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

@Component
public class CustomerIdGenerator implements IdentifierGenerator {

    public CustomerIdGenerator() {
        short workId = 0;
        var options = new IdGeneratorOptions(workId);
        YitIdHelper.setIdGenerator(options);
        System.out.println("+++++++++init+++++++++");
    }

    @Override
    public Long nextId(Object entity) {
        return YitIdHelper.nextId();
    }
}
