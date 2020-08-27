package fr.lygaen.asciax.src.mongodb;

import com.mongodb.client.result.InsertOneResult;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import static fr.lygaen.asciax.src.mongodb.DBConfig.guildsCollection;
import static fr.lygaen.asciax.src.mongodb.DBWrapper.findByKey;

public class DBGuild {

    private String prefix = fr.lygaen.asciax.src.others.Config.prefix;
    private Guild guild;
    private Boolean isPrenium = false;

    public DBGuild(Guild guild) {
        this.guild = guild;
    }

    public Document convert() {
        return new Document()
                .append("id", this.guild.getId())
                .append("prenium", this.isPrenium)
                .append("prefix", this.prefix);
    }

    public void resetTo(Guild guild, Boolean isPrenium, String prefix) {
        this.guild = guild;
        this.isPrenium = isPrenium;
        this.prefix = prefix;
    }

    public InsertOneResult push(Boolean isPrenium, String prefix) {
        return guildsCollection.insertOne(new Document()
                .append("id", this.guild.getId())
                .append("prenium", this.isPrenium)
                .append("prefix", this.prefix));
    }

    public Document get() {
        return findByKey(guildsCollection, "id", this.guild.getId());
    }
}
