import React, { useEffect } from "react";
import { Modal, Form, Input } from "antd";
import InputMask from 'react-input-mask';

const NewVote = ({ visible, onCreate, onCancel }) => {
  const [form] = Form.useForm();

  useEffect(() => {
    if (visible) {
      form.resetFields();
    }
  }, [visible]);

  const CPFInput = (props) => {
    return (
      <InputMask mask="999.999.999-99" value={props.value} onChange={props.onChange}>
        {() => <Input {...props} />}
      </InputMask>
    );
  };
  
  
  return (
    <Modal
      title="Votar"
      open={visible}
      onOk={() => form.submit()}
      onCancel={onCancel}
    >
      <Form
        form={form}
        layout="vertical"
        onFinish={(values) => {
          onCreate(values);
          form.resetFields();
        }}
      >
        <Form.Item
          name="associatedCpf"
          label="Digite seu CPF"
          rules={[
            { required: true, message: "Por favor insira o título da pauta!" },
          ]}
        >
          <CPFInput />
        </Form.Item>
      </Form>
    </Modal>
  );
};


export default NewVote;