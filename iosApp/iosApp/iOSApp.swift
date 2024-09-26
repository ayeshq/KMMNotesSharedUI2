import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
//         NotesModuleKt.initializeKoinForIOS()
        IOSModuleKt.initializeKoinForIOS()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}