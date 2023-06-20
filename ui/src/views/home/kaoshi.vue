<template>
  <el-button @click="getQuestion()">点我获取题目</el-button>
  <div>
    <div v-for="question in questions" :key="question.questionid">
      <question
          :question="question.question"
          :options="question.options"
          :value="selectedOptions[question.questionid]"
          @option-selected="updateSelectedOptions(question.questionid, $event)"
      />
    </div>
    <el-button @click="submit">提交</el-button>
  </div>
</template>

<script>
import Question from '@/components/MultipleQuestion';
import {CommitQuestion, getQuestion} from "@/api/kaoshi";
import {ElMessage} from "element-plus";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name:"kaoshi",
  components: {
    Question
  },
  data() {
    return {
      tempquestion:[],
      questions: [
        {
          questionid:1,
          question: '问题1',
          options: ['选项1', '选项2', '选项3']
        },
        {
          questionid:5,
          question: '问题2',
          options: ['选项A', '选项B', '选项C', '选项D']
        },
        // 可以添加更多的题目
      ],
      selectedOptions: {} // 用数组来存储每个题目的选项值
    };
  },
  methods: {
    updateSelectedOptions(index, option) {
      console.log("id为:"+index)
      let charCodeA = 'A'.charCodeAt(0);  // 获取字符 'A' 的 ASCII 码值
      let charCode = charCodeA + option;
      let convertedChar = String.fromCharCode(charCode);
      console.log("选项为："+convertedChar)
      this.selectedOptions[index]=convertedChar;
      // this.selectedOptions.splice(index, 1, option);
    },
    option(type,message){
      ElMessage({
        message: message,
        type: type,
      })
    },
    submit() {
      console.log('提交答案：', this.selectedOptions);
      let questionss = [];

      for (let key in this.selectedOptions) {
        let questionn = {
          questionId: key,
          questionAnwser: this.selectedOptions[key]
        };
        questionss.push(questionn);
      }
      console.log(questionss);
      CommitQuestion(questionss).then(response=>{
        console.log(response.data)
        if(response.data.code===200){
          this.option("success",response.data.msg)
          this.$router.push('/history')
        }else {
          this.option("error",response.data.msg)
        }
      }) .catch(error => {
        this.option("error","服务器错误请重试！")
        // 请求失败处理
        console.error(error);
      });
    },
    getQuestion(){
      getQuestion().then(response=>{
        // var tempqu = response.data.data()
        console.log(response.data.data)
        this.questions = response.data.data.map(item => {
          return {
            questionid:item.questionId,
            question: item.questionContext,
            options: [
              item.anwserA,
              item.anwserB,
              item.anwserC,
              item.anwserD
            ]
          };
        });
      })
    }
  }
};
</script>
