import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, m, rx, ry, bx, by, ox, oy;
    static int turn = 11;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    ox = i;
                    oy = j;
                } else if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<Integer[]> moves = new LinkedList<>();
        for (int d = 0; d < 4; d++) {
            Integer[] m = move(rx, ry, bx, by, d, 0);
            if (m.length == 5) {
                moves.offer(m);
            } else if (m.length == 2) {
                turn = m[1];
                break;
            }
        }
        if (turn != 11) {
            System.out.println(turn);
            return;
        }

        while (!moves.isEmpty()) {
            Integer[] c = moves.poll();
            for (int d = 0; d < 4; d++) {
                Integer[] m = move(c[0], c[1], c[2], c[3], d, c[4]);
                if (m.length == 5) {
                    moves.offer(m);
                } else if (m.length == 2) {
                    turn = m[1];
                    break;
                }
            }
            if (turn != 11) break;
        }

        System.out.println(turn == 11 ? -1 : turn);
    }

    // 주어진 R, B 위치에서 d 방향으로 움직이는게 가능한지
    // 움직였다 == rx ry bx by tries
    // 실패 == 0
    // 성공 == 1 tries
    static Integer[] move(int crx, int cry, int cbx, int cby, int d, int tries) {
        if (tries == 10) return new Integer[] {0};
        int rx = crx;
        int ry = cry;
        int bx = cbx;
        int by = cby;
        boolean holer = false;
        boolean holeb = false;
        // 왼
        if (d == 0) {
            // 만약에 같은 선상에 있으면
            if (crx == cbx) {
                // 레드 먼저 움직이기
                if (cry < cby) {
                    while (true) {
                        cry--;
                        if (board[crx][cry] == '#') {
                            cry++;
                            break;
                        // 넣었다
                        } else if (board[crx][cry] == 'O') {
                            holer = true;
                            break;
                        }
                    }
                    while (true) {
                        cby--;
                        // 충돌
                        if (!holer && cby == cry) {
                            cby++;
                            break;
                        } else if (board[cbx][cby] == '#') {
                            cby++;
                            break;
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                } else {
                    while (true) {
                        cby--;
                        if (board[cbx][cby] == '#') {
                            cby++;
                            break;
                            // 넣었다
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                    if (!holeb) {
                        while (true) {
                            cry--;
                            // 충돌
                            if (cry == cby) {
                                cry++;
                                break;
                            } else if (board[crx][cry] == '#') {
                                cry++;
                                break;
                            } else if (board[crx][cry] == 'O') {
                                holer = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                while (true) {
                    cry--;
                    if (board[crx][cry] == '#') {
                        cry++;
                        break;
                        // 넣었다
                    } else if (board[crx][cry] == 'O') {
                        holer = true;
                        break;
                    }
                }
                while (true) {
                    cby--;
                    if (board[cbx][cby] == '#') {
                        cby++;
                        break;
                        // 넣었다
                    } else if (board[cbx][cby] == 'O') {
                        holeb = true;
                        break;
                    }
                }
            }
            if (!holeb && holer) {
                return new Integer[] {1, tries + 1};
            } else if (holeb) {
                return new Integer[] {0};
            } else {
                if (ry == cry && by == cby) {
                    return new Integer[] {0};
                }
                return new Integer[] {crx, cry, cbx, cby, tries + 1};
            }
        }

        // 오
        else if (d == 1) {
            // 만약에 같은 선상에 있으면
            if (crx == cbx) {
                // 레드 먼저 움직이기
                if (cry > cby) {
                    while (true) {
                        cry++;
                        if (board[crx][cry] == '#') {
                            cry--;
                            break;
                            // 넣었다
                        } else if (board[crx][cry] == 'O') {
                            holer = true;
                            break;
                        }
                    }
                    while (true) {
                        cby++;
                        // 충돌
                        if (!holer && cby == cry) {
                            cby--;
                            break;
                        } else if (board[cbx][cby] == '#') {
                            cby--;
                            break;
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                } else {
                    while (true) {
                        cby++;
                        if (board[cbx][cby] == '#') {
                            cby--;
                            break;
                            // 넣었다
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                    if (!holeb) {
                        while (true) {
                            cry++;
                            // 충돌
                            if (cry == cby) {
                                cry--;
                                break;
                            } else if (board[crx][cry] == '#') {
                                cry--;
                                break;
                            } else if (board[crx][cry] == 'O') {
                                holer = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                while (true) {
                    cry++;
                    if (board[crx][cry] == '#') {
                        cry--;
                        break;
                        // 넣었다
                    } else if (board[crx][cry] == 'O') {
                        holer = true;
                        break;
                    }
                }
                while (true) {
                    cby++;
                    if (board[cbx][cby] == '#') {
                        cby--;
                        break;
                        // 넣었다
                    } else if (board[cbx][cby] == 'O') {
                        holeb = true;
                        break;
                    }
                }
            }
            if (!holeb && holer) {
                return new Integer[] {1, tries + 1};
            } else if (holeb) {
                return new Integer[] {0};
            } else {
                if (ry == cry && by == cby) {
                    return new Integer[] {0};
                }
                return new Integer[] {crx, cry, cbx, cby, tries + 1};
            }
        }

        // 위
        else if (d == 2) {
            // 만약에 같은 선상에 있으면
            if (cry == cby) {
                // 레드 먼저 움직이기
                if (crx < cbx) {
                    while (true) {
                        crx--;
                        if (board[crx][cry] == '#') {
                            crx++;
                            break;
                            // 넣었다
                        } else if (board[crx][cry] == 'O') {
                            holer = true;
                            break;
                        }
                    }
                    while (true) {
                        cbx--;
                        // 충돌
                        if (!holer && cbx == crx) {
                            cbx++;
                            break;
                        } else if (board[cbx][cby] == '#') {
                            cbx++;
                            break;
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                } else {
                    while (true) {
                        cbx--;
                        if (board[cbx][cby] == '#') {
                            cbx++;
                            break;
                            // 넣었다
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                    if (!holeb) {
                        while (true) {
                            crx--;
                            // 충돌
                            if (crx == cbx) {
                                crx++;
                                break;
                            } else if (board[crx][cry] == '#') {
                                crx++;
                                break;
                            } else if (board[crx][cry] == 'O') {
                                holer = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                while (true) {
                    crx--;
                    if (board[crx][cry] == '#') {
                        crx++;
                        break;
                        // 넣었다
                    } else if (board[crx][cry] == 'O') {
                        holer = true;
                        break;
                    }
                }
                while (true) {
                    cbx--;
                    if (board[cbx][cby] == '#') {
                        cbx++;
                        break;
                        // 넣었다
                    } else if (board[cbx][cby] == 'O') {
                        holeb = true;
                        break;
                    }
                }
            }
            if (!holeb && holer) {
                return new Integer[] {1, tries + 1};
            } else if (holeb) {
                return new Integer[] {0};
            } else {
                if (rx == crx && bx == cbx) {
                    return new Integer[] {0};
                }
                return new Integer[] {crx, cry, cbx, cby, tries + 1};
            }
        }

        // 아래
        else {
            // 만약에 같은 선상에 있으면
            if (cry == cby) {
                // 레드 먼저 움직이기
                if (crx > cbx) {
                    while (true) {
                        crx++;
                        if (board[crx][cry] == '#') {
                            crx--;
                            break;
                            // 넣었다
                        } else if (board[crx][cry] == 'O') {
                            holer = true;
                            break;
                        }
                    }
                    while (true) {
                        cbx++;
                        // 충돌
                        if (!holer && cbx == crx) {
                            cbx--;
                            break;
                        } else if (board[cbx][cby] == '#') {
                            cbx--;
                            break;
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                } else {
                    while (true) {
                        cbx++;
                        if (board[cbx][cby] == '#') {
                            cbx--;
                            break;
                            // 넣었다
                        } else if (board[cbx][cby] == 'O') {
                            holeb = true;
                            break;
                        }
                    }
                    if (!holeb) {
                        while (true) {
                            crx++;
                            // 충돌
                            if (crx == cbx) {
                                crx--;
                                break;
                            } else if (board[crx][cry] == '#') {
                                crx--;
                                break;
                            } else if (board[crx][cry] == 'O') {
                                holer = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                while (true) {
                    crx++;
                    if (board[crx][cry] == '#') {
                        crx--;
                        break;
                        // 넣었다
                    } else if (board[crx][cry] == 'O') {
                        holer = true;
                        break;
                    }
                }
                while (true) {
                    cbx++;
                    if (board[cbx][cby] == '#') {
                        cbx--;
                        break;
                        // 넣었다
                    } else if (board[cbx][cby] == 'O') {
                        holeb = true;
                        break;
                    }
                }
            }
            if (!holeb && holer) {
                return new Integer[] {1, tries + 1};
            } else if (holeb) {
                return new Integer[] {0};
            } else {
                if (rx == crx && bx == cbx) {
                    return new Integer[] {0};
                }
                return new Integer[] {crx, cry, cbx, cby, tries + 1};
            }
        }
    }
}