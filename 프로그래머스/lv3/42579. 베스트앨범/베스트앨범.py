def solution(genres, plays):
    genre_dict = {}
    for i in range(len(genres)):
        if genres[i] not in genre_dict:
            genre_dict[genres[i]] = plays[i]
        else:
            genre_dict[genres[i]] += plays[i]
    genre_list = []
    for genre in genre_dict:
        genre_list.append([genre, genre_dict[genre]])
    genre_list.sort(key = lambda x : -x[1])
    
    result = []
    # 장르가 단 한개
    if len(genre_list) == 1:
        sorted_plays = sorted(plays, reverse = True)
        # 노래가 단 한개
        if len(plays) == 1:
            result = [0]
        # 노래가 여러개
        else:
            for i in range(2):
                result.append(plays.index(sorted_plays[i]))
                plays[plays.index(sorted_plays[i])] = -1
    # 장르가 여러개
    else:
        for i in range(len(genre_list)):
            genre = genre_list[i][0]
            plays_list = []
            for j in range(len(plays)):
                if genres[j] == genre:
                    plays_list.append([plays[j], j])
            plays_list.sort(key = lambda x : (-x[0], x[1]))
            # 장르의 노래가 단 한개
            if len(plays_list) == 1:
                result.append(plays_list[0][1])
            # 노래가 여러개
            else:
                for k in range(2):
                    result.append(plays_list[k][1])
    
    return result
                                    
        
    
        
