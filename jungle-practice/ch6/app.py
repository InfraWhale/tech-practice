from flask import Flask, render_template, jsonify, request
app = Flask(__name__)

import requests
from bs4 import BeautifulSoup

from bson import ObjectId
from pymongo import MongoClient
client = MongoClient('mongodb+srv://sparta:jungle@cluster0.8hcdxo3.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0')
db = client.dbpractice

## HTML을 주는 부분
@app.route('/')
def home():
   return render_template('index.html')

@app.route('/memo', methods=['GET'])
def listing():
    # 1. 모든 document 찾기 & _id 값은 출력에서 제외하기
    result = list(db.cards.find({}))
   
    for i in result:
        i['_id'] = str(i['_id'])

    print(result)

    if len(result) > 0 :
        return jsonify({'result':'success', 'cards':result, 'msg':'GET 성공!'})
    else :
        return jsonify({'result':'fail', 'msg':'결과 없음'})
## API 역할을 하는 부분
@app.route('/memo', methods=['POST'])
def saving():
    print("check1", request.form);
    title_request = request.form['title_request']
    text_request = request.form['text_request']

    card = {
        'title': title_request, 
        'text': text_request
    }

    db.cards.insert_one(card)
    return jsonify({'result': 'success', 'msg':'POST 성공!'})

@app.route('/memo/update', methods=['POST'])
def update():
    id_request = request.form['id_give']
    title_request = request.form['title_give']
    text_request = request.form['text_give']

    db.cards.update_one({"_id" : ObjectId(id_request)}, {'$set' : {"title" : title_request, "text" : text_request}})

    return jsonify({'result': 'success', 'msg': '업데이트 완료!'})

@app.route('/memo/delete', methods=['POST'])
def delete():
    id_request = request.form['id_give']
    db.cards.delete_one({"_id" : ObjectId(id_request)})
    return jsonify({'result': 'success', 'msg': 'delete 완료!'})

if __name__ == '__main__':
   app.run('0.0.0.0',port=5000,debug=True)