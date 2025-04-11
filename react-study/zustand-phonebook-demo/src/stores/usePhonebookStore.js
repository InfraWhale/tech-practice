import {create} from "zustand";

const usePhoneBookStore = create((set) => ({
    phoneBook: [],
  
    addContact: (name, phoneNumber) =>
      set((state) => ({
        phoneBook: [...state.phoneBook, { id: Date.now(), name, phoneNumber }],
      })),
  
    searchInput: "", // 입력 중인 텍스트
    searchKeyword: "", // 실제 검색 적용 대상
  
    setSearchInput: (input) => set({ searchInput: input }),
    applySearch: () =>
      set((state) => ({ searchKeyword: state.searchInput })),
  }));

export default usePhoneBookStore;