<template>
    <el-container>
        <el-aside width="200px">
            <!--            导航栏-->
            <side-menu></side-menu>


        </el-aside>
        <el-container>
            <el-header>
                <strong>VueAdmin后台管理系统</strong>
                <div class="header-avatar">
                    <el-avatar size = "medium" :src="userInfo.avatar"></el-avatar>
                    <el-dropdown>
                          <span class="el-dropdown-link">
                            {{userInfo.username}}<i class="el-icon-arrow-down el-icon--right"></i>
                          </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <router-link :to="{name: 'UserCenter'}">个人中心</router-link>
                            </el-dropdown-item>
                            <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                    <el-link href="https://leslieaibin.github.io/" target="_blank">github</el-link>
                    <el-link href="https://space.bilibili.com" target="_blank">B站</el-link>
                </div>
            </el-header>
            <el-main>
                <Tabs></Tabs>
                <div style="margin: 0 15px;">
                    <router-view/>
                </div>

            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    // 引用
    import SideMenu from "./inc/SideMenu";
    import Tabs from "./inc/Tabs";
    // 声明
    export default {
        name: "Home",
        components: {Tabs, SideMenu},
        comments:{
            SideMenu, Tabs
        },
        data(){
            return{
                userInfo:{
                    id:"",
                    username:"",
                    avatar:"",
                }
            }
        },
        created() {
            this.getUserInfo()
        },
        methods:{
            getUserInfo(){
                this.$axios.get("/sys/userInfo").then(res =>{
                    this.userInfo = res.data.data
                })
            },
            logout(){
                this.$axios.post("/logout").then(res =>{
                    // 删除信息
                    localStorage.clear()
                    sessionStorage.clear()
                    this.$store.commit("resrtState")
                    this.$router.push("/login")

                })
            },
        }
    }
</script>

<style scoped>
    .el-container{
        padding: 0;
        margin: 0;
        height: 100vh;
    }

    .header-avatar {
        float: right;
        width: 210px;
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .el-dropdown-link {
        cursor: pointer;
    }
    .el-header{
        background-color:  #17B3A3;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: left;
        line-height: 200px;
    }


    .el-menu-vertical-demo {
        height: 100vh;
    }

    .el-main {
        color: #333;
        padding: 0 !important;
    }

    /*消除下划线*/
    a {
        text-decoration: none;
    }
</style>