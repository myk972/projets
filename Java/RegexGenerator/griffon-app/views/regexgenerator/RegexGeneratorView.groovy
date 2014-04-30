package regexgenerator

import java.awt.BorderLayout

application(title: 'RegexGenerator',
        preferredSize: [1000, 1000],
        pack: true,
        //location: [50,50],
        locationByPlatform: true,
        iconImage: imageIcon('/griffon-icon-48x48.png').image,
        iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                imageIcon('/griffon-icon-32x32.png').image,
                imageIcon('/griffon-icon-16x16.png').image]) {
    // add content here
    borderLayout()
    panel(constraints: BorderLayout.CENTER, border: compoundBorder([emptyBorder(10), titledBorder('Define your constaints:')])) {
        tableLayout {
            tr {
                td {
                    label "first constraint"
                }
            }
            tr {
                td {
                    label "2e constraint"
                }
            }
            tr {
                td {
                    button(text: "add")
                }
            }
        }
    }
    button(constraints: BorderLayout.SOUTH, text: "validate")
}
