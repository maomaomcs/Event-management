import request from '@/utils/request'

// 查询队伍列表
export function listTeams(query) {
  return request({
    url: '/system/teams/list',
    method: 'get',
    params: query
  })
}

// 查询队伍详细
export function getTeams(id) {
  return request({
    url: '/system/teams/' + id,
    method: 'get'
  })
}

// 新增队伍
export function addTeams(data) {
  return request({
    url: '/system/teams',
    method: 'post',
    data: data
  })
}

// 修改队伍
export function updateTeams(data) {
  return request({
    url: '/system/teams',
    method: 'put',
    data: data
  })
}

// 删除队伍
export function delTeams(id) {
  return request({
    url: '/system/teams/' + id,
    method: 'delete'
  })
}
