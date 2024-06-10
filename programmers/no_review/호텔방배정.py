import sys
sys.setrecursionlimit(10**6)
def find(rooms, room):
    if room not in rooms:
        rooms[room] = room
    if rooms[room] != room:
        rooms[room] = find(rooms, rooms[room]) 
    return rooms[room]

def union(rooms, room1, room2):
    rooms[room1] = room2

def solution(k, room_number):
    rooms = {}
    result = []

    for room in room_number:
        available_room = find(rooms, room)
        result.append(available_room)
        if available_room + 1 <= k:
            union(rooms, available_room, available_room + 1)
    
    return result