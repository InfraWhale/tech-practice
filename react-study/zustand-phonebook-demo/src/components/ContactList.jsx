import React from 'react';
import usePhoneBookStore from '../stores/usePhonebookStore';

const ContactList = () => {
  const { phoneBook, searchKeyword } = usePhoneBookStore();

  // 검색어가 포함된 이름만 필터링
  const filtered = phoneBook.filter((item) =>
    item.name?.toLowerCase().includes(searchKeyword?.toLowerCase() || "")
  );

  return (
    <div>
      {filtered.map((item) => (
        <div key={item.id}>
          <p>{item.name}</p>
          <p>{item.phoneNumber}</p> 
        </div>
      ))}
    </div>
  );
};

export default ContactList;