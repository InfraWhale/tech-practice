from pymongo import MongoClient

client = MongoClient('mongodb+srv://sparta:jungle@cluster0.8hcdxo3.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0')
db = client.dbjungle

# doc = {
#     'name':'영수',
#     'age':24
# }
# db.users.insert_one(doc)
# db.users.insert_one({'name':'영희', 'age':30})
# db.users.insert_one({'name':'철수', 'age':20})
# db.users.insert_one({'name':'john', 'age':30})

# all_users = list(db.users.find())
# all_users = list(db.users.find({}, {'_id':False}))
# print(all_users[0])
# print(all_users[0]['name'])
# for a in all_users:
#     print(a)

# user = db.users.find_one({}, {'_id':False})
# print(user)

# db.users.update_one({'name':'영수'}, {'$set':{'age':19}})
# user = db.users.find_one({'name':'영수'}, {'_id':False})
# print(user)

db.users.delete_one({'name':'영수'})
user = db.users.find_one({'name': '영수'})
print(user)