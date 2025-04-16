import {create} from "zustand";

const useQueryStore = create((set) => ({
    
    searchInput: "",
    searchQuery: "", // 실제 검색 적용 대상
    
    setinput: (input) => set({ searchInput: input }),
    applyQuery: () =>
      set((state) => ({ searchQuery: state.searchInput })),
    }));

export default useQueryStore;