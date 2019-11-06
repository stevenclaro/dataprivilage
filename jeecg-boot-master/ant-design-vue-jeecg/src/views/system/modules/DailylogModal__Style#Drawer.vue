<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="附件" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'accessory', validatorRules.accessory]" placeholder="请输入附件"></a-input>
        </a-form-item>
        <a-form-item label="今日完成工作" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'finishWorkToday', validatorRules.finishWorkToday]" placeholder="请输入今日完成工作"></a-input>
        </a-form-item>
        <a-form-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'image', validatorRules.image]" placeholder="请输入图片"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>
        <a-form-item label="未完成工作" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'unfinishedWork', validatorRules.unfinishedWork]" placeholder="请输入未完成工作"></a-input>
        </a-form-item>
        <a-form-item label="需要协调的工作" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'workToBeCoordinated', validatorRules.workToBeCoordinated]" placeholder="请输入需要协调的工作"></a-input>
        </a-form-item>
        <a-form-item label="填报人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'filler', validatorRules.filler]" placeholder="请输入填报人"></a-input>
        </a-form-item>
        <a-form-item label="填报时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择填报时间" v-decorator="[ 'filldate', validatorRules.filldate]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="明日工作" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'workTomorrow', validatorRules.workTomorrow]" placeholder="请输入明日工作"></a-input>
        </a-form-item>
        <a-form-item label="预留字段" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'reserve', validatorRules.reserve]" placeholder="请输入预留字段"></a-input>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "DailylogModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules:{
        accessory:{},
        finishWorkToday:{},
        image:{},
        remark:{},
        unfinishedWork:{},
        workToBeCoordinated:{},
        filler:{},
        filldate:{},
        workTomorrow:{},
        reserve:{},
        },
        url: {
          add: "/dailylog/dailylog/add",
          edit: "/dailylog/dailylog/edit",
        }
     
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'accessory','finishWorkToday','image','remark','unfinishedWork','workToBeCoordinated','filler','filldate','workTomorrow','reserve'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'accessory','finishWorkToday','image','remark','unfinishedWork','workToBeCoordinated','filler','filldate','workTomorrow','reserve'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>