fruits = ['사과','배','배','감','수박','귤','딸기','사과','배','수박'];

f_dict ={};

for fruit in fruits:
    if fruit in f_dict :
        f_dict[fruit] += 1;
    else :
        f_dict[fruit] = 1;

for element in f_dict:
    print(element, f_dict[element]);