import request from '@/utils/request'

// 查询队伍信息列表
export function listTeamInfo(query) {
  return request({
    url: '/system/TeamInfo/list',
    method: 'get',
    params: query
  })
}

// 查询队伍信息详细
export function getTeamInfo(teamid) {
  return request({
    url: '/system/TeamInfo/' + teamid,
    method: 'get'
  })
}

// 新增队伍信息
export function addTeamInfo(data) {
  return request({
    url: '/system/TeamInfo',
    method: 'post',
    data: data
  })
}

// 修改队伍信息
export function updateTeamInfo(data) {
  return request({
    url: '/system/TeamInfo',
    method: 'put',
    data: data
  })
}

// 删除队伍信息
export function delTeamInfo(teamid) {
  return request({
    url: '/system/TeamInfo/' + teamid,
    method: 'delete'
  })
}
