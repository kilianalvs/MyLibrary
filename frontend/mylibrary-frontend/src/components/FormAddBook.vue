<template>
  <div class="form-container">
    <h1>Ajoute un Livre</h1>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="title">Titre</label>
        <input
            type="text"
            id="title"
            v-model="formData.title"
            placeholder="Titre ici"
            required
        />
      </div>
      <div class="form-group">
        <label for="author">Auteur</label>
        <input
            type="text"
            id="author"
            v-model="formData.author"
            placeholder="Auteur ici"
            required
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea
            id="description"
            v-model="formData.description"
            placeholder="Entrez votre description"
            required
        ></textarea>
      </div>
      <div class="form-group">
        <label for="published_date">Date de Publication</label>
        <input
            type="date"
            id="published_date"
            v-model="formData.published_date"
            required
        />
      </div>
      <div class="form-actions">
        <button type="submit">Confirmer</button>
        <button type="button" @click="resetForm">Réinitialiser</button>
      </div>
    </form>
  </div>
</template>

<script>
import {ref} from "vue";

export default {
  name: "AddBookForm",
  setup() {
    const formData = ref({
      title: "",
      author: "",
      description: "",
      published_date: "",
    });

    const handleSubmit = async () => {
      try {
        const response = await fetch("http://localhost:9000/book", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData.value),
        });

        if (!response.ok) {
          throw new Error("Erreur lors de l'envoi des données.");
        }

        alert("Données envoyées avec succès !");
        resetForm();
      } catch (error) {
        console.error("Erreur :", error);
        alert("Une erreur est survenue. Veuillez réessayer.");
      }
    };

    const resetForm = () => {
      formData.value = {
        title: "",
        author: "",
        description: "",
        published_date: "",
      };
    };

    return {
      formData,
      handleSubmit,
      resetForm,
    };
  },
};
</script>

<style scoped>
</style>