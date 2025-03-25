import time

from mysql.connector import connect

from mook import mook_user_operation
from update_music_id import load_music_ids

# 点赞、取消点赞、收藏、取消收藏、播放、搜索
if __name__ == '__main__':
    conn = connect(user='root', password='123456', database='music163', host='192.168.43.101')
    cur = conn.cursor()
    music_id_cle = load_music_ids()
    for i in range(50):
        cur.execute('insert into user_action(uid, music_id, likes, plays, favs) values (%s, %s, %s, %s, %s)',
                    mook_user_operation(music_id_cle))
        conn.commit()
        # time.sleep(.1)
    cur.close()
    conn.close()
