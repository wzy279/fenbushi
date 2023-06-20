<template>
  <div class="question">
    <div class="title" v-html="question"/>
    <el-radio-group v-model="selectedOption" @change="updateSelectedOption">
      <el-radio v-for="(option, index) in options" :label="index" :key="index">{{ option }}</el-radio>
    </el-radio-group>
  </div>
  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
</template>

<script>
export default {
  name: "MultipleQuestion",
  props: {
    question: {
      type: String,
      required: true
    },
    options: {
      type: Array,
      required: true
    },
    value: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      selectedOption: this.value
    };
  },
  methods: {
    updateSelectedOption() {
      this.$emit('option-selected', this.selectedOption);
    }
  }
};
</script>

<style scoped>
.question {
  margin-bottom: 20px;
}
.title {
  font-weight: bold;
  margin-bottom: 10px;
}
</style>

