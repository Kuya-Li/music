import random


def mook_user_operation(music_id_cle):
    uid = random.randint(0, 10)
    music_id = random.choice(music_id_cle)
    likes = random.randint(0, 1)
    plays = random.randint(0, 1)
    favs = random.randint(0, 1)
    return uid, music_id, likes, plays, favs
