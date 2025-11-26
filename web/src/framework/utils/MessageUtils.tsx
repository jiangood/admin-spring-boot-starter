import {Input, message, Modal, notification} from 'antd';
import type {ModalFuncProps} from 'antd/es/modal/interface';
import type {ArgsProps, NotificationPlacement} from 'antd/es/notification/interface';
import React from 'react';
import {ThemeUtils} from "./system";

// --- MsgUtils 类 (包含静态方法的工具类) ---


const buttonStyles = {
    root: {
        backgroundColor: ThemeUtils.getColor('primary-color')
    }
}

/**
 * 消息工具类 (MsgUtils)
 * 封装了 Ant Design 的 Modal, message, 和 notification 静态方法
 */
export class MessageUtils {
    // --- Antd Modal 封装 (Alert/Confirm/Prompt) ---

    /**
     * 弹出 Alert 提示框
     */
    static alert(content: React.ReactNode, config?: Omit<ModalFuncProps, 'content' | 'icon' | 'onOk' | 'onCancel'>) {
        Modal.info({
            title: '提示',
            content,
            okText: '确定',
            icon: null,
            okButtonProps: {
                styles: buttonStyles
            },
            ...config,
        });
    }

    /**
     * 弹出 Confirm 确认框
     */
    static confirm(content: React.ReactNode, config?: Omit<ModalFuncProps, 'content' | 'icon' | 'onOk' | 'onCancel'>) {
        return new Promise((resolve) => {
            Modal.confirm({
                title: '确认操作',
                content,
                okText: '确定',
                cancelText: '取消',
                onOk: () => resolve(true),
                onCancel: () => resolve(false),
                okButtonProps: {
                    styles: buttonStyles
                },
                ...config,
            });
        })

    }

    /**
     * 弹出 Prompt 输入框对话框
     */
    static prompt(message: React.ReactNode, initialValue?: string, placeholder?: string, config?: Omit<ModalFuncProps, 'content' | 'title' | 'icon' | 'onOk'>) {
        return new Promise((resolve) => {

            const ref = React.createRef()
            Modal.confirm({
                title: '提示',
                content: <div>
                    <div style={{marginBottom: 4}}>{message}</div>
                    <Input ref={ref} placeholder={placeholder}/>
                </div>,
                okText: '确定',
                cancelText: '取消',
                onOk: () => {
                    const inputInstance = ref.current;
                    const inputElement = inputInstance.input;
                    const inputValue = inputElement.value;
                    resolve(inputValue)
                },
                onCancel: () => {
                    resolve()
                },
                okButtonProps: {
                    styles: buttonStyles
                },
                ...config,
            });
        })
    }

    // --- Antd message 封装 (全局通知/Loading) ---

    /**
     * 成功消息
     */
    static success(content: React.ReactNode, duration: number = 3) {
        message.success(content, duration);
    }

    /**
     * 错误消息
     */
    static error(content: React.ReactNode, duration: number = 3) {
        message.error(content, duration);
    }

    /**
     * 警告消息
     */
    static warning(content: React.ReactNode, duration: number = 3) {
        message.warning(content, duration);
    }

    /**
     * 通用消息
     */
    static info(content: React.ReactNode, duration: number = 3) {
        message.info(content, duration);
    }

    /**
     * 弹出 Loading 提示
     */
    static loading(content: React.ReactNode = '正在加载...', duration?: number) {
        return message.loading({content, duration: duration === undefined ? 0 : duration});
    }

    /**
     * 立即关闭所有 message 提示
     */
    static hideAll() {
        message.destroy();
    }

    // --- Antd Notification 封装 (通知提醒框) ---

    /**
     * 弹出右上角通知提醒框
     */
    static notify(
        message: React.ReactNode,
        description: React.ReactNode,
        type: 'success' | 'error' | 'info' | 'warning' | 'open' | 'config' = 'open',
        placement: NotificationPlacement = 'topRight',
        config?: Omit<ArgsProps, 'message' | 'description' | 'placement'>,
    ) {
        const notifyFunc = notification[type];
        notifyFunc({
            message,
            description,
            placement,
            ...config,
        });
    }

    /**
     * 弹出成功通知
     */
    static notifySuccess(
        message: React.ReactNode,
        description: React.ReactNode,
        placement?: NotificationPlacement,
        config?: Omit<ArgsProps, 'message' | 'description' | 'placement'>,
    ) {
        MessageUtils.notify(message, description, 'success', placement, config);
    }

    /**
     * 弹出失败通知
     */
    static notifyError(
        message: React.ReactNode,
        description: React.ReactNode,
        placement?: NotificationPlacement,
        config?: Omit<ArgsProps, 'message' | 'description' | 'placement'>,
    ) {
        MessageUtils.notify(message, description, 'error', placement, config);
    }

    /**
     * 弹出警告通知
     */
    static notifyWarning(
        message: React.ReactNode,
        description: React.ReactNode,
        placement?: NotificationPlacement,
        config?: Omit<ArgsProps, 'message' | 'description' | 'placement'>,
    ) {
        MessageUtils.notify(message, description, 'warning', placement, config);
    }
}

