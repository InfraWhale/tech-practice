from flask import Flask, render_template, jsonify, request
app = Flask(__name__)

import requests

from bson import ObjectId
from pymongo import MongoClient
client = MongoClient('')
db = client.dbpractice

## HTML을 주는 부분
@app.route('/')
def home():
   return render_template('index.html')

## todo 전체조회
@app.route('/todo', methods=['GET'])
def todo():
    result = list(db.todos.find({}))
   
    for i in result:
        i['_id'] = str(i['_id'])

    if len(result) > 0 :
        return jsonify({'result':'success', 'lists':result, 'msg':'GET 성공!'})
    else :
        return jsonify({'result':'fail', 'msg':'결과 없음'})

## todo 입력
@app.route('/todo', methods=['POST'])
def save():
    text_request = request.form['text_request']
    status_request = request.form['status_request']
    todo = {
        'text': text_request,
        'status': status_request
    }

    db.todos.insert_one(todo)
    return jsonify({'result': 'success', 'msg':'POST 성공!'})

## 상태 업데이트
@app.route('/todo/updateStatus', methods=['POST'])
def updateStatus():
    id_request = request.form['id_give']
    status_request = request.form['status_give']
    msg_response = ''
    if status_request == 'done':
        msg_response = '할 일 체크 완료!'
    else :
        msg_response = '할 일 완료 취소!'
    db.todos.update_one({"_id" : ObjectId(id_request)}, {'$set' : {"status" : status_request}})

    return jsonify({'result': 'success', 'msg': msg_response})

## text 업데이트
@app.route('/todo/updateText', methods=['POST'])
def updateText():
    id_request = request.form['id_give']
    text_request = request.form['text_give']

    db.todos.update_one({"_id" : ObjectId(id_request)}, {'$set' : {"text" : text_request}})

    return jsonify({'result': 'success', 'msg': '할 일 업데이트 완료!'})

## todo 삭제
@app.route('/todo/delete', methods=['POST'])
def delete():
    id_request = request.form['id_give']
    db.todos.delete_one({"_id" : ObjectId(id_request)})
    return jsonify({'result': 'success', 'msg': '할 일 삭제 완료!'})

if __name__ == '__main__':
   app.run('0.0.0.0',port=5000,debug=True)