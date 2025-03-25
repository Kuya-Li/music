from kafka import KafkaProducer
from kafka.errors import kafka_errors
import traceback
import json

from mook import mook_user_operation
from update_music_id import load_music_ids

if __name__ == '__main__':
    music_id_cle = load_music_ids()
    producer = KafkaProducer(
        bootstrap_servers=['localhost:9092'], value_serializer=lambda x: json.dumps(x).encode('utf-8')
    )
    for i in range(500):
        resp = producer.send('first', mook_user_operation(music_id_cle))
        try:
            resp.get(10)
        except kafka_errors:
            traceback.format_exc()
