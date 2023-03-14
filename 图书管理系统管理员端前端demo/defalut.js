{
    const aside = document.querySelector("aside")
    const logoName = aside.querySelector(".logo-name")
    const asideLogoBox = aside.querySelector(".aside-logo-box")
    const asideLogo = aside.querySelector(".aside-logo")
    const main = document.querySelector("main")
    const header = document.querySelector("header")
    let input = document.querySelector("input")
    input.value = "提示：小明，小红，小蓝，小绿借阅已超时，请督促及时归还图书"
    aside.addEventListener("mouseenter", function (event){
        event.stopPropagation()
        aside.className = ""
        main.style.paddingLeft = "220px"
        asideLogoBox.className = "aside-logo-box"
        asideLogo.className = "aside-logo"
        header.style.marginLeft = "220px"
        logoName.style.color = "black"
    })

    aside.addEventListener("mouseleave", function (event){
        event.stopPropagation()
        aside.className = "aside-close"
        main.style.paddingLeft = "60px"
        asideLogoBox.className = "aside-logo-box-little"
        asideLogo.className = "aside-logo-little"
        header.style.marginLeft = "60px"
        logoName.style.color = "#234199"
    })
}