# Hanling-Lib
一个Minecraft模组开发API，提供了一系列实用工具
该模组仍在开发中，后续功能会不断完善
# 功能
## 通用
1.对原版关键帧动画系统的拓展，将其适用范围由`HierarchicalModel`拓展到所有实体模型

2.tick实用工具

3.渲染实用工具

4.生物AI框架
## 仅Fabric端
本模组在Fabric上实现了部分ForgeApi,以便于将模组由Forge移植到Fabric，目前已实现以下功能

1.延迟注册器

2.事件系统及部分Forge上的事件

3.网络通信系统

4.部分Forge上的IForge接口拓展的原版类中的方法
# 部署开发环境
1.在Releases中下载带有-dev后缀的开发包，注意不要下错了

2.在你的项目的根目录下新建一个lib文件夹，然后把下载的开发包放进去

3.打开bulid.gradle，在dependencies中添加以下代码
```
	implementation fileTree(dir:'lib',includes:['*jar'])
```
如果是Fabric端，还需要将你的项目采用的映射表改为mojangmappings，将dependencies中的mappings项修改为
```
	mappings loom.officialMojangMappings()
```
之后刷新gradle项目，Fabric端还需要运行fabric中的genSources任务，Eclipse可能还需要重新导入项目
# 如何使用
具体使用方法可参考GitHub的wiki界面（之后会尽快更新）