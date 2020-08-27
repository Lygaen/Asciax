package fr.lygaen.asciax.src.mongodb;

import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import static fr.lygaen.asciax.src.mongodb.DBConfig.guildsCollection;
import static fr.lygaen.asciax.src.mongodb.DBWrapper.findByKey;

public class DBGuild {

    public String prefix = fr.lygaen.asciax.src.others.Config.prefix;
    private Guild guild;
    public Boolean isPrenium = false;

    public DBGuild(Guild guild) {
        this.guild = guild;
        this.update();
    }

    public Document convert(String prefix, Boolean prenium) {
        return new Document()
                .append("guild_id", this.guild.getId())
                .append("prenium", prenium)
                .append("prefix", prefix);
    }

    public void resetTo(Guild guild, Boolean isPrenium, String prefix) {
        this.guild = guild;
        this.isPrenium = isPrenium;
        this.prefix = prefix;
    }

    public InsertOneResult push() {
        return guildsCollection.insertOne(new Document()
                .append("guild_id", this.guild.getId())
                .append("prenium", this.isPrenium)
                .append("prefix", this.prefix));
    }


    public void update() {
        Document document = findByKey(guildsCollection, "guild_id", this.guild.getId());

        if(document != null) {
            this.isPrenium = document.getBoolean("prenium");
            this.prefix = document.getString("prefix");
        }
    }

    public UpdateResult change(String prefix) {
        return guildsCollection.replaceOne(this.convert(this.prefix, this.isPrenium), this.convert(prefix, this.isPrenium));
    }
}
