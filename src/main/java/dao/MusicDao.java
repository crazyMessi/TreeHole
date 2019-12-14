package dao;

import entities.Music;

import java.util.List;

/**
 * @author 19892
 */
public class MusicDao extends BaseDao<Music> {
    //获得随机一首歌
    public List<Music> getMusicList(String tag){return this.list(Music.class,"SELECT * FROM `music` WHERE `tag`=? ORDER BY RAND() LIMIT 1;",tag);}

    //增加歌
    public Music addMusic(String musicUrl,String imgUrl,String tag,String musicName){
        int code;
        Music music=new Music();
        musicUrl=musicUrl.substring(25);
        imgUrl=imgUrl.substring(25);
        String sql="INSERT INTO `music` (musicUrl,imgUrl,tag,musicName)VALUE(?,?,?,?)";
        code=update(sql,musicUrl,imgUrl,tag,musicName);
        if (code!=-1){
            music=find(Music.class,"SELECT*FROM music WHERE musicUrl=? AND imgUrl=?;",musicUrl,imgUrl);
        }
        return music;
    }
}

