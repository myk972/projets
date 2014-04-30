application {
    title = 'RegexGenerator'
    startupGroups = ['regexGenerator']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "regexGenerator"
    'regexGenerator' {
        model      = 'regexgenerator.RegexGeneratorModel'
        view       = 'regexgenerator.RegexGeneratorView'
        controller = 'regexgenerator.RegexGeneratorController'
    }

}
