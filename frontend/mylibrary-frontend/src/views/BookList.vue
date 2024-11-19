<template>
  <div>
    <h1>Liste des Livres</h1>
    <ul>
      <li v-for="book in books" :key="book.id">
        {{ book.title }} - {{ book.author }}
      </li>
    </ul>
  </div>
  <FormAddBook></FormAddBook>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import apiClient from '../services/api';
import FormAddBook from "../components/FormAddBook.vue";

interface Book {
  id: number;
  title: string;
  author: string;
}

export default defineComponent({
  name: 'BookList',
  components: {FormAddBook},
  setup() {
    const books = ref<Book[]>([]);

    const fetchBooks = async () => {
      try {
        const response = await apiClient.get<Book[]>('/books');
        books.value = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération des livres :', error);
      }
    };

    onMounted(fetchBooks);

    return {
      books,
    };
  },
});
</script>