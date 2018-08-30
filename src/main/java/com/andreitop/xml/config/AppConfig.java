package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@ComponentScan("com.andreitop.xml")
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {
    @Bean
    public Mount frostWolf() {
        return new Wolf();
    }

    @Bean
    public Mount shadowTiger() {
        return new Tiger();
    }

    @Bean
    public Unit knight() {
        return new Human(shadowTiger(), "thunderFury", "soulBlade");
    }

    @Bean
    public Unit trall() {
        Orc trall = new Orc(frostWolf());
        trall.setWeapon("furryAxe");
        trall.setColorCode(9);
        return trall;
    }

    @Bean
    public SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("dd/mm/yyyy");
    }

    @Bean
    public Map<String, Mount> trollMountMap() {
        HashMap<String, Mount> map = new HashMap<>();
        map.put("m1", frostWolf());
        map.put("m2", shadowTiger());
        return map;
    }

    @Bean
    public Set<Mount> trollMountSet() {
        HashSet<Mount> set = new LinkedHashSet<>();
        set.add(shadowTiger());
        set.add(frostWolf());
        return set;
    }

    @Bean List<Mount> trollMountList() {
        ArrayList<Mount> list = new ArrayList<>();
        list.add(Troll.DEFAULT_MOUNT);
        list.add(null);
        list.add(shadowTiger());
        return list;
    }

    @Value("${character.created}")
    private String characterCreated;

    @Bean
    public Unit zulJin() throws ParseException {
        Troll zulJin = new Troll();
        zulJin.setColorCode(java.util.concurrent.ThreadLocalRandom.current().nextInt(2, 10));
        zulJin.setCreationDate(dateFormatter().parse(characterCreated));
        zulJin.setMapOfMounts(trollMountMap());
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setListOfMounts(trollMountList());
        return zulJin;
    }
}
