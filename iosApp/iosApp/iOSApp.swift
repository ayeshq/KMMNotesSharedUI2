import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        NotesModuleKt.initializeKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}