import csv

from mysql.connector import connect


def load_music_ids():
    res = []
    with open('music_ids.csv') as fp:
        reader = csv.reader(fp)
        for row in reader:
            res.append(row[0])
    return res


if __name__ == '__main__':
    conn = connect(user='root', password='123456', database='music163', host='192.168.43.101')
    cur = conn.cursor()
    cur.execute('select distinct music_id from music')
    music_id_cle = cur.fetchall()
    with open('music_ids.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        for i in music_id_cle:
            writer.writerow(i)
    cur.close()
    conn.close()
