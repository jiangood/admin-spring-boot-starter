import {IApi} from 'umi';
import Utils from "./Utils";


function convertFileToRoute(file) {
    const absPath = file;
    const mainName = Utils.getFileMainName(file)

    // 只添加小写开头的文件页面，大写的默认为组件
    if (mainName.charAt(0) !== mainName.charAt(0).toLowerCase()) {
        return
    }

    let routePath = file.substring(file.indexOf('pages') + 6, file.length - 4)
    routePath = routePath.replaceAll("\$", ":")    // 文件$开头的会替换为路径变量 如$id 变为 :id

    let parentId = "@@/global-layout";

    if (routePath.endsWith("/index")) {
        routePath = routePath.substring(0, routePath.length - 6)
    }
    return {
        absPath,
        id: routePath,
        path: routePath,
        file,
        parentId
    }
}




export default (api: IApi) => {

    api.describe({
        key: 'admin-spring-boot-stater-route',
    });

    api.logger.info('plugin starting... ')

    const frameworkDirs = Utils.getDirs(api.paths.absNodeModulesPath + "/@jiangood");
    api.logger.info('依赖的框架：', frameworkDirs)

    for (let frameworkDir of frameworkDirs) {
        api.logger.info("正在解析文件夹", frameworkDir)

        // 处理路由
        const routeFiles = Utils.findFilesSync(frameworkDir, /src\/pages\/.*\.jsx$/) // src/pages/**
        api.logger.info("找到的页面文件：", routeFiles)
        api.modifyRoutes((routes) => {
            for (let file of routeFiles) {
                const route = convertFileToRoute(file)
                if(route){
                    if (routes[route.id] == null) { //  如果用户项目没有这个路由，则加入。否则以用户项目为准
                        api.logger.info("加入路由:", route.id, "路径:",route.absPath)
                        routes[route.id] = route
                    }
                }
            }
            api.logger.info("路由修改完成")
            return routes;
        })
    }
};