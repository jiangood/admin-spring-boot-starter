import fs from 'fs';
import path from 'path';

export default class Utils {

    /**
     * 获取目录下的所有子目录
     * @param dir
     * @returns {string[]}  完整路径
     */
    static getDirs(dir) {
        console.log('获取dir....', dir)

        if (!fs.existsSync(dir)) throw new Error(`目录不存在: ${dir}`);


        return fs.readdirSync(dir).map(item => {
            const fullPath = path.join(dir, item);
            if (fs.statSync(fullPath).isDirectory()) {
                return path.join(dir, item)
            }
        });
    }

    /**
     * 同步获取目录下所有文件
     */
    static getAllFilesSync(dir) {
        if (!fs.existsSync(dir)) throw new Error(`目录不存在: ${dir}`);

        const results = [];

        function traverse(currentDir) {
            fs.readdirSync(currentDir).forEach(item => {
                const fullPath = path.join(currentDir, item);
                if (fs.statSync(fullPath).isDirectory()) {
                    traverse(fullPath);
                } else {
                    results.push(fullPath.split(path.sep).join('/'));
                }
            });
        }

        traverse(dir);


        return results;
    }

    /**
     * 同步查找匹配模式的文件
     *
     * @return 文件列表 全路径
     */
    static findFilesSync(dir, pattern: RegExp) {
        const files = this.getAllFilesSync(dir);

        return files.filter(file => pattern.test(file)).map(file => file.split(path.sep).join('/'))
    }

    /**
     * 获取文件名（不含后缀）
     * @param {string} filePath - 文件路径
     * @returns {string} 文件名（不含后缀）
     */
    public static getFileMainName(filePath) {
        // 从路径中提取文件名
        const fileName = path.basename(filePath);

        // 移除扩展名
        const lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }

        return fileName; // 没有扩展名的情况
    }
}

