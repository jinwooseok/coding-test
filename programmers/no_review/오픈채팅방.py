def solution(record):
    answer = []
    def enter(name):
        return name+"님이 들어왔습니다."
    def leave(name):
        return name+"님이 나갔습니다."

    methods = {"Enter":enter, "Leave":leave}
    names = {}
    for rec in record:
        rec=rec.split(" ")
        if rec[0] == "Enter" or rec[0]=="Change":
            names[rec[1]] = rec[2]

    for rec in record:
        rec=rec.split(" ")
        if rec[0] == 'Change':
            continue
        answer.append(methods[rec[0]](names[rec[1]]))
            
    return answer