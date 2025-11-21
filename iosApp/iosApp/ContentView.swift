import SwiftUI
import Shared

// 1
// struct ContentView: View {
//     @State private var showContent = false
//     var body: some View {
//         VStack {
//             Button("Click me!") {
//                 withAnimation {
//                     showContent = !showContent
//                 }
//             }
//
//             if showContent {
//                 VStack(spacing: 16) {
//                     Image(systemName: "swift")
//                         .font(.system(size: 200))
//                         .foregroundColor(.accentColor)
//                     Text("SwiftUI: \(Greeting().greet())")
//                 }
//                 .transition(.move(edge: .top).combined(with: .opacity))
//             }
//         }
//         .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
//         .padding()
//     }
// }

// //2
// struct ContentView: View {
//     let phrases = Greeting().greet()
//
//     var body: some View {
//         List(phrases, id: \.self) {
//             Text($0)
//         }
//     }
// }

//3
struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        ListView(phrases: viewModel.greetings)
            .task {
                await self.viewModel.startObserving()
            }
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings: Array<String> = []

        func startObserving() async {
            for await phrase in Greeting().greet() {
                self.greetings.append(phrase)
            }
        }
    }
}

struct ListView: View {
    let phrases: Array<String>

    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}
