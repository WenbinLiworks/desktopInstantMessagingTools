/*
 * 消息发送，文件传输发送
 */
#ifndef CHAT_H
#define CHAT_H

#include <QWidget>
#include <QMouseEvent>
#include <QtGui>
#include <QDebug>
#include <QScrollBar>
#include <QFile>
#include <QFileDialog>
#include "IM/user.h"
#include "protocol/jspp.h"
#include <QUdpSocket>
#include <QTcpSocket>
#include "dialogrec.h"

#define TCP

namespace Ui {
class Chat;
}

class Chat : public QWidget
{
    Q_OBJECT
   // Conversation *conv;
    User *peer_user;
    QTimer *timer;
    string peer_ip;
    string peer_port;
    DialogRec *dia;

    QTcpSocket *tcpsocket;
    bool connected;

public:
    explicit Chat(QWidget *parent, User *peer_user);
    ~Chat();
    QPoint move_point; //移动的距离
    bool mouse_press; //鼠标按下
    //鼠标按下事件
    void mousePressEvent(QMouseEvent *event);
    //鼠标释放事件
    void mouseReleaseEvent(QMouseEvent *event);
    //鼠标移动事件
    void mouseMoveEvent(QMouseEvent *event);
    //最小化及关闭
    bool eventFilter(QObject *object, QEvent *e);

    //-------以下函数 仅用作离线消息
    int  sendOffMsg(std::string peer_id, std::string msg);
    void reqMsg();
private slots:
    void connectedSlot();
    void disConnected();
    void readMessage();
    void errorSlot();
    void initMsgSocket();
    //-------以上函数 仅用作离线消息

protected:
    void showEvent(QShowEvent *event);

private slots:

    void checkMsg();
    void on_sndBtn_clicked();

    void recvMsg(QString msg);
    void recvMsg(vector<JSPP> msg_vec);

    void on_sndFileBtn_clicked();
    void sendFile(QString filePath, QString fileport);

private:
    Ui::Chat *ui;
    QFileDialog *fDialog;
    void setIP_port();
#ifdef TCP
    QTcpServer * tcpServer; //监听
    QTcpSocket * fileSocket; //通信
    QFile file;             //文件对象
    QString fileName;       //文件名字
    qint64 fileSize;        //文件大小
    qint64 sendSize;        //已经发送大小
    //QTimer filetimer;           //定时器
    void sendHeader();
    void initFileSocket();
#endif
    string initFileServer();
    void sendData();
    QString insertLeft(QString msg);
    QString insertRight(QString msg);
};

#endif // CHAT_H
