import { app, BrowserWindow, ipcMain, protocol } from 'electron';
import * as path from 'path';
import * as url from 'url';

let mainWindow: BrowserWindow;
let modalWindow: BrowserWindow;

function createWindow() {
  // Create the browser window.
  mainWindow = new BrowserWindow({
    height: 1440,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: false,
      nodeIntegration: true,
    },
    width: 1900,
    maximizable: false,
  });
  // and load the index.html of the app.
  mainWindow.loadFile(path.join(__dirname, '../src/pages/login/login.html'));

  // Open the DevTools.
  mainWindow.webContents.openDevTools();

  ipcMain.on('trocar-conteudo', (event, novoArquivo) => {
    mainWindow.loadFile(path.join(__dirname, novoArquivo));
    event.sender.send(
      'conteudo-trocado',
      `Conteudo trocado para ${novoArquivo}`,
    );
  });

  // Registre um ouvinte IPC para abrir o modal
  ipcMain.on('open-modal', async (event: Event, filePath: string) => {
    await createModalWindow(filePath);
  });

  ipcMain.on('fechar-modal', async () => {
    // Feche a modal quando receber a mensagem 'fechar-modal'
    if (modalWindow) {
      modalWindow.close();
    }
  });
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.whenReady().then(() => {
  createWindow();

  app.on('activate', function () {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

// In this file you can include the rest of your app"s specific main process
// code. You can also put them in separate files and require them here.

// let mainWindow: BrowserWindow | null;

function createModalWindow(filePath: string) {
  modalWindow = new BrowserWindow({
    width: 1000,
    height: 800,
    frame: false,
    parent: mainWindow,
    modal: true,
    webPreferences: {
      nodeIntegration: true,
    },
  });

  modalWindow.loadFile(filePath); // Use o caminho fornecido para carregar o HTML do modal

  modalWindow.on('closed', () => {
    // Realize ações quando o modal for fechado, se necessário
  });
}
